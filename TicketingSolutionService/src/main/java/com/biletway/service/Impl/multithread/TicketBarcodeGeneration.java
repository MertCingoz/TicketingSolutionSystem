package com.biletway.service.Impl.multithread;

import java.math.BigInteger;

import com.biletway.dao.entity.Event;
import com.biletway.dao.entity.Show;
import com.biletway.dao.entity.TicketPool;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.EventDao;
import com.biletway.dao.interfaces.ShowDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.util.ApplicationContextUtil;

import bwt.tools.barcodegenerator.BWTBarcodeGenerator;

public class TicketBarcodeGeneration implements Runnable {
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	ShowDao showDao = (ShowDao) ApplicationContextUtil.getApplicationContext().getBean("showDao");
	EventDao eventDao = (EventDao) ApplicationContextUtil.getApplicationContext().getBean("eventDao");

	BigInteger eventId;
	BigInteger showId;

	public TicketBarcodeGeneration(BigInteger eventId, BigInteger showId) {
		this.eventId = eventId;
		this.showId = showId;
	}

	@Override
	public void run() {
		Show showEnt = showDao.getById(showId);
		BWTBarcodeGenerator barcodeGenerator = BWTBarcodeGenerator.getInstance();
		for (int i = 0; i < showEnt.getTicketCount(); i++) {
			TicketPool tp = new TicketPool();
			String barcode = barcodeGenerator.createBarcode(eventId.toString());
			tp.setBarcode(barcode);
			tp.setEventId(this.eventId);
			tp.setStatus(DaoConstants.ACTIVE_STATUS);
			commonOperationsDao.insert(tp);
			System.out.println("inserted barcode : ".concat(barcode));
		}
		Event eventEnt = eventDao.getById(eventId);
		eventEnt.setBarcodeCreationStatus("COMPLETED");
		commonOperationsDao.update(eventEnt);
	}
}
