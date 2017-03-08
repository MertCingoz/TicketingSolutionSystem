package com.biletway.service.interfaces;

import com.biletway.service.response.ServiceResponse;

public interface MenuService {
	ServiceResponse getMenuTree(String userId);
}
