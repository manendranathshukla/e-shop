package com.mycodeworks.eshop.model;

public class PaymentDetails {
	
	private String paymentMethod;
	private String status;
	private String payementId;
	private String razorpayPayementLinkId;
	private String razorpayPaymentLinkReferenceId;
	
	private String razorpayPaymentLinkStatus;
	
	private String razorpayPaymentId;

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayementId() {
		return payementId;
	}

	public void setPayementId(String payementId) {
		this.payementId = payementId;
	}

	public String getRazorpayPayementLinkId() {
		return razorpayPayementLinkId;
	}

	public void setRazorpayPayementLinkId(String razorpayPayementLinkId) {
		this.razorpayPayementLinkId = razorpayPayementLinkId;
	}

	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}

	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}

	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}

	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public PaymentDetails(String paymentMethod, String status, String payementId, String razorpayPayementLinkId,
			String razorpayPaymentLinkReferenceId, String razorpayPaymentLinkStatus, String razorpayPaymentId) {
		super();
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.payementId = payementId;
		this.razorpayPayementLinkId = razorpayPayementLinkId;
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public PaymentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
