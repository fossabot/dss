package eu.europa.esig.dss.validation.process.bbb.xcv.checks;

import eu.europa.esig.dss.jaxb.detailedreport.XmlXCV;
import eu.europa.esig.dss.validation.MessageTag;
import eu.europa.esig.dss.validation.policy.rules.Indication;
import eu.europa.esig.dss.validation.policy.rules.SubIndication;
import eu.europa.esig.dss.validation.process.bbb.AbstractMultiValuesCheckItem;
import eu.europa.esig.dss.validation.reports.wrapper.CertificateWrapper;
import eu.europa.esig.jaxb.policy.MultiValuesConstraint;

public class SurnameCheck extends AbstractMultiValuesCheckItem<XmlXCV> {

	private final CertificateWrapper certificate;

	public SurnameCheck(XmlXCV result, CertificateWrapper certificate, MultiValuesConstraint constraint) {
		super(result, constraint);

		this.certificate = certificate;
	}

	@Override
	protected boolean process() {
		return processValueCheck(certificate.getSurname());
	}

	@Override
	protected MessageTag getMessageTag() {
		return MessageTag.BBB_XCV_ISCGSURN;
	}

	@Override
	protected MessageTag getErrorMessageTag() {
		return MessageTag.BBB_XCV_ISCGSURN_ANS;
	}

	@Override
	protected Indication getFailedIndicationForConclusion() {
		return Indication.INVALID;
	}

	@Override
	protected SubIndication getFailedSubIndicationForConclusion() {
		return SubIndication.SIG_CONSTRAINTS_FAILURE;
	}

}
