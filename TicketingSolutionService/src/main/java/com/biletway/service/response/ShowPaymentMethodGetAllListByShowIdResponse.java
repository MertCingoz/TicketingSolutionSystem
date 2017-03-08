package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.ShowPaymentMethod;

public class ShowPaymentMethodGetAllListByShowIdResponse {
	List<ShowPaymentMethod> showPaymentMethodList;

	public List<ShowPaymentMethod> getShowPaymentMethodList() {
		return showPaymentMethodList;
	}

	public void setShowPaymentMethodList(List<ShowPaymentMethod> showPaymentMethodList) {
		this.showPaymentMethodList = showPaymentMethodList;
	}

}
