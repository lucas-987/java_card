package test_jc2;

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;

public class applet2 extends Applet {
	
	private static final byte CLA_MONAPPLET = 0;
	
	public static final short SW_CARD_BLOCKED = 0x6600;
	public static final short SW_CARD_SUSPENDED = 0x6601;
	public static final short SW_CARD_DEAD = 0x6602;
	
	public static final byte INS_EMPRUNT_VELO = 0x00;
	public static final byte INS_RESTITUTION_VELO = 0x01;
	public static final byte INS_SUSPENTION_CARTE = 0x03;
	public static final byte INS_DEBLOQUER_CARTE = 0x05;
	public static final byte INS_RECHARGER_SOLDE = 0x06;
	public static final byte INS_CONSULTER_SOLDE = 0x09;
	public static final byte INS_ASK_PIN = 0x07;
	public static final byte INS_ASK_PUK = 0x08;
	
	public static final byte STATUS_NORMAL = 0x00;
	public static final byte STATUS_BLOQUEE = 0x01;
	public static final byte STATUS_MORTE = 0x02;
	public static final byte STATUS_BANNIE = 0x03;
	
	private byte card_status = STATUS_NORMAL;

	private applet2() {
	}

	public static void install(byte bArray[], short bOffset, byte bLength)
			throws ISOException {
		new applet2().register();
	}

	public void process(APDU apdu) throws ISOException {
		byte[] buffer = apdu.getBuffer();

		if (this.selectingApplet()) return;

		if (buffer[ISO7816.OFFSET_CLA] != CLA_MONAPPLET) {
			ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
		}
		
		switch (buffer[ISO7816.OFFSET_INS]) {
			case INS_EMPRUNT_VELO :
				if(checkCardStatus()) {
					
				}
				break;
				
			case INS_RESTITUTION_VELO :
				if(checkCardStatus()) {
					
				}
				break;
				
			case INS_SUSPENTION_CARTE :
				if(checkCardStatus()) {
					
				}
				break;
				
			case INS_DEBLOQUER_CARTE:
				break;
				
			case INS_RECHARGER_SOLDE:
				if(checkCardStatus()) {
					
				}
				break;
				
			default:
				ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
		}
	}
	
	//return true if card_status == STATUS_NORMAL, throw appropriate exception otherwise
	public boolean checkCardStatus() {
		switch(card_status) {
			case STATUS_NORMAL:
				return true;
			case STATUS_BLOQUEE:
				ISOException.throwIt(SW_CARD_BLOCKED);
			case STATUS_BANNIE:
				ISOException.throwIt(SW_CARD_SUSPENDED);
			case STATUS_MORTE:
				ISOException.throwIt(SW_CARD_DEAD);
		}
		
		return false;
	}

}
