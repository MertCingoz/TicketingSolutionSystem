package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.PaymentMethod;

public class PaymentMethodGetAllListResponse {
	List<PaymentMethod> paymentMethodList;

	public List<PaymentMethod> getPaymentMethodList() {
		return paymentMethodList;
	}

	public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {
		this.paymentMethodList = paymentMethodList;
	}

}
