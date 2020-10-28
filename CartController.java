package jp.co.internous.mushrooms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.mushrooms.model.domain.TblCart;
import jp.co.internous.mushrooms.model.domain.dto.CartDto;
import jp.co.internous.mushrooms.model.form.CartForm;
import jp.co.internous.mushrooms.model.mapper.TblCartMapper;
import jp.co.internous.mushrooms.model.session.LoginSession;

@Controller
@RequestMapping("/mushrooms/cart")
public class CartController {

	@Autowired
	private LoginSession loginSession;

	@Autowired
	private TblCartMapper cartMapper;

	private Gson gson = new Gson();

	@RequestMapping("/")
	public String index(Model model) {

		// ログインと未ログインで分岐しuserIdに代入
		long userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();

		// DBからカート情報をSelect
		List<CartDto> cartList = cartMapper.selectCart(userId);
		model.addAttribute("loginSession", loginSession);
		model.addAttribute("cartList", cartList);

		return "cart";
	}

	@RequestMapping("/add")
	public String addCart(CartForm f, Model model) {

		// ログインと未ログインで分岐しuserIdに代入
		long userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();

		f.setUserId(userId);

		// カート作成＆値をセット
		TblCart cart = new TblCart(f);

		// 重複数をintで返却
		int overlap = cartMapper.findByUserIdAndProductId(userId, f.getProductId());

		// 重複があればUpdate
		if (overlap > 0) {
			cartMapper.updateCart(cart);
		} else {
			cartMapper.insertCart(cart);
		}

		// DBからカート情報をSelect
		List<CartDto> cartList = cartMapper.selectCart(userId);
		model.addAttribute("loginSession", loginSession);
		model.addAttribute("cartList", cartList);

		return "cart";
	}

	@SuppressWarnings("unchecked") // 警告をオフ
	@ResponseBody
	@RequestMapping("/delete")
	public boolean deleteCart(@RequestBody String deleteIdList) {

		// 受け取った値をMapに格納していく
		Map<String, List<String>> map = gson.fromJson(deleteIdList, Map.class);
		// mapからListに再び格納
		List<String> deleteIds = map.get("deleteIdList");

		int result = 0;

		// ループ処理でカート情報を削除
		for (String deleteId : deleteIds) {
			result += cartMapper.deleteByCartId(Long.parseLong(deleteId));
		}

		return result > 0;
	}

}