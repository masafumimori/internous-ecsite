package jp.co.internous.mushrooms.model.mapper;

import jp.co.internous.mushrooms.model.domain.TblCart;
import jp.co.internous.mushrooms.model.domain.dto.CartDto;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TblCartMapper {
	
	//カート抽出時
	List<CartDto> selectCart(@Param("userId") long userId);

	//重複チェック
	@Select("SELECT count(id) FROM tbl_cart WHERE user_id = #{userId} AND product_id = #{productId}")
	int findByUserIdAndProductId(@Param("userId") long userId, @Param("productId") long productId);

	//カート追加時
	@Insert("INSERT INTO tbl_cart (user_id, product_id, product_count)" +
			"VALUES (#{userId}, #{productId}, #{productCount})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insertCart(TblCart tblCart);

	//TmpUserIdをUserIdで更新
	@Update("UPDATE tbl_cart SET user_id = #{userId}, updated_at = NOW() WHERE user_id = #{tmpUserId}")
	int updateByUserId(@Param("userId") long userId, @Param("tmpUserId") long tmpUserId);

	//ユーザーに紐づくカートをカウント
	@Select("SELECT count(user_id) FROM tbl_cart WHERE user_id = #{userId}")
	int findCountByUserId(@Param("userId") long userId);	

	//カート更新時
	@Update("UPDATE tbl_cart SET product_count = product_count + #{productCount}, updated_at = NOW()" +
			"WHERE user_id = #{userId} AND product_id = #{productId}")
	int updateCart(TblCart tblCart);

	//カートから商品を削除時
	@Delete("DELETE FROM tbl_cart WHERE id = #{deleteId}")
	int deleteByCartId(@Param("deleteId") long deleteId);

	//決済後のカート削除時
	@Delete("DELETE FROM tbl_cart WHERE user_id = #{userId}")
	int deleteByUserId(@Param("userId") long userId);

}