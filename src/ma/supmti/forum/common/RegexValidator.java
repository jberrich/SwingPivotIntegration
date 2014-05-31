package ma.supmti.forum.common;

import org.apache.pivot.wtk.validation.RegexTextValidator;

public class RegexValidator extends RegexTextValidator {

	public RegexValidator(String regexKey) {
		super(Config.getInstance().getResource(regexKey));
	}

	public boolean isValid(String text) {
		if(text.isEmpty()) return true;
		return super.isValid(text);
	}

}
