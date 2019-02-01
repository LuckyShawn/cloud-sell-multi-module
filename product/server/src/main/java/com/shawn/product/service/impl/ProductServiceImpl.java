package com.shawn.product.service.impl;

import com.shawn.product.common.DecreaseStockInput;
import com.shawn.product.entities.ProductInfo;
import com.shawn.product.enums.ProductStatusEnum;
import com.shawn.product.enums.ResultEnum;
import com.shawn.product.exception.ProductException;
import com.shawn.product.repository.ProductInfoRepository;
import com.shawn.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Description TODO
 * @Author shawn
 * @create 2019/1/29 0029
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {
        for(DecreaseStockInput decreaseStockInput : cartDTOList){
            Optional<ProductInfo> productInfoRepositoryById = productInfoRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if(!productInfoRepositoryById.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //判断库存是否足够
            ProductInfo productInfo = productInfoRepositoryById.get();
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if(result < 0 ){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
