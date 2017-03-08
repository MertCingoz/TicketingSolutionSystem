package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.PaymentMethod;

public interface PaymentMethodDao {
	PaymentMethod getById(BigInteger id);

	List<PaymentMethod> getAllList();
}
