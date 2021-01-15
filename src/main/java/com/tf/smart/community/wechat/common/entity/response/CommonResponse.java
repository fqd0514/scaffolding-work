package com.tf.smart.community.wechat.common.entity.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.enums.ReturnTypeEnum;

/**
 * 通用响应消息
 *
 * @author 翟晶
 **/
public class CommonResponse<T> {
    // 状态
    private Integer code;

    // 信息
    private String message;

    // 返回类型枚举类
    private ReturnTypeEnum returnType;

    // 返回值
    private T data;

    // 总数据量
    private Long total;

    // 页数
    private long pageCount;

    // 当前页
    private Long pageNum;

    public static CommonResponse success() {
        return success(null);
    }

    public static <T> CommonResponse success(T data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(CommonResponseEnum.SUCCESS.getCode());
        commonResponse.setMessage(CommonResponseEnum.SUCCESS.getMessage());
        commonResponse.setReturnType(ReturnTypeEnum.SINGLE_OBJECT);
        commonResponse.setData(data);

        return commonResponse;
    }

    public static CommonResponse error(CommonResponseEnum commonResponseEnum) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(commonResponseEnum.getCode());
        commonResponse.setMessage(commonResponseEnum.getMessage());

        return commonResponse;
    }

    public static CommonResponse error(CommonResponseEnum commonResponseEnum, String message) {
        CommonResponse commonResponse = CommonResponse.error(commonResponseEnum);
        commonResponse.setMessage(message);

        return commonResponse;
    }

    public static CommonResponse error(Long errorCode, String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(errorCode.intValue());
        commonResponse.setMessage(message);

        return commonResponse;
    }

    public static <T> CommonResponse page(IPage<T> pageInfo) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(CommonResponseEnum.SUCCESS.getCode());
        commonResponse.setMessage(CommonResponseEnum.SUCCESS.getMessage());
        commonResponse.setReturnType(ReturnTypeEnum.LIST_WITH_PAGE);
        commonResponse.setData(pageInfo.getRecords());
        commonResponse.setTotal(pageInfo.getTotal());
        commonResponse.setPageCount(pageInfo.getPages());
        commonResponse.setPageNum(pageInfo.getCurrent());

        return commonResponse;
    }

    /**
     * 设置分页信息
     * @param pageInfo
     */
    public void setPageInfo(IPage<T> pageInfo){
        if (null==pageInfo) {
            return;
        }
        setCode(CommonResponseEnum.SUCCESS.getCode());
        setMessage(CommonResponseEnum.SUCCESS.getMessage());
        setReturnType(ReturnTypeEnum.LIST_WITH_PAGE);
        setData((T) pageInfo.getRecords());
        setTotal(pageInfo.getTotal());
        setPageCount(pageInfo.getPages());
        setPageNum(pageInfo.getCurrent());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnTypeEnum getReturnType() {
        return returnType;
    }

    public void setReturnType(ReturnTypeEnum returnType) {
        this.returnType = returnType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public boolean isSuccess() {
        return this.code.equals(CommonResponseEnum.SUCCESS.getCode());
    }

    public boolean isError() {
        return !isSuccess();
    }
}
