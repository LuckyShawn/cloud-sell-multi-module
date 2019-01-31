package com.shawn.product.controller;

import com.shawn.product.DTO.CartDTO;
import com.shawn.product.VO.ProductInfoVO;
import com.shawn.product.VO.ProductVO;
import com.shawn.product.VO.ResultVO;
import com.shawn.product.entities.ProductCategory;
import com.shawn.product.entities.ProductInfo;
import com.shawn.product.service.CategoryService;
import com.shawn.product.service.ProductService;
import com.shawn.product.utils.ResultVOUtils;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 商品Controller
 * @Author shawn
 * @create 2019/1/30 0030
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list(){
        //1. 查询所有在架的商品
        List<ProductInfo> productInfoList =  productService.findUpAll();
        //2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : categoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                //类目相同才设值
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
            return ResultVOUtils.success(productVOList);

    }

    /**
     * 获取商品列表（给订单服务用的接口）
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody  List<String> productIdList){
        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList){
        productService.decreaseStock(cartDTOList);
    }

    /**
     * 测试一下lambda表达式
     * @param args
     */
    public static void main(String[] args){
      //将list的所有String转换成大写
      List<String> list = new ArrayList<>();
      list.add("aaa");
      list.add("bbb");
      list = list.stream().map(String :: toUpperCase).collect(Collectors.toList());
      System.out.println(list);

      //过滤集合，取大于2的值
      List<Integer > list1 = new ArrayList<Integer>();
      list1.add(1);
      list1.add(2);
      list1.add(3);
      list1.add(4);
      list1.add(5);
      list1.add(6);
      System.out.println(list1.stream().filter(a  -> a >2 ).collect(Collectors.toList()));
    }
}
