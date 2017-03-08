package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.ShowPaymentMethod;

public interface ShowPaymentMethodDao {

	ShowPaymentMethod getById(BigInteger id);

	List<ShowPaymentMethod> getPaymentMethodsByShowId(BigInteger showId);
}
