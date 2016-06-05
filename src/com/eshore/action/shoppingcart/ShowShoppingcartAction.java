package com.eshore.action.shoppingcart;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshore.action.Action;
import com.eshore.factory.DAOFactory;
import com.eshore.pojo.Goods;
import com.eshore.pojo.ShoppingCart;
import com.eshore.pojo.TempGoods;
import com.eshore.tag.PageObject;

public class ShowShoppingcartAction implements Action {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//新建TempGoods对象
		Vector<TempGoods> tempVector = new Vector<TempGoods>();
		//获取登录用户的ID
		String uids = String.valueOf(request.getSession().getAttribute("uid"));
		int uid = Integer.parseInt(uids);
		float countPrice = 0.0f;
		//获取分页对象PageObject
				//PageObject pageObject = PageObject.getInstance(request);//ceshi测试

		try{
			System.out.println("我在这未支付购物车");//ceshi测试
			//获取用户的所有未支付的购物车列表
			Vector<ShoppingCart> busVector = DAOFactory.getShoppingCartDAOInstance().
					getAppointedGoods(uid, 0);
			for (int i = 0; i < busVector.size(); i++) {
				ShoppingCart cart = new ShoppingCart();
				cart = (ShoppingCart) busVector.get(i);//获取购物车
				Goods good=new Goods();
				TempGoods tempGoods = new TempGoods();
				Vector<Goods> gVector=DAOFactory.getGoodDAOInstance().
					queryGoodBySid(cart.getGid());//获取指定商品
				if(gVector.size()>0&&gVector!=null)
					good =(Goods)gVector.get(0); 
				//组合TempGoods对象
				tempGoods.setGood(good);
				tempGoods.setNumber(cart.getNumber());
				tempVector.add(tempGoods);
				countPrice+=cart.getNumber()*good.getPrice();//计算价格
			}
			//String curPage = request.getParameter("curPage");//获取当前页//ceshi测试
			//pageObject = DAOFactory.getGoodDAOInstance().//向页面传送分页内容//ceshi测试
				//getPageObject(curPage, pageObject, tempVector);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("goods", tempVector);
		request.setAttribute("countPrice",countPrice);
		System.out.println("1我在这未支付购物车");//ceshi测试
		return "shoppingcart/bus.jsp";
	}
}
