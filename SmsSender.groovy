import com.gargoylesoftware.htmlunit.*
import com.gargoylesoftware.htmlunit.html.*

class SmsSender {
	def sendSms(def message, def username, def password) {
		WebClient webClient = new WebClient();
		webClient.setJavaScriptEnabled(false)
		HtmlPage page = webClient.getPage("https://oma.saunalahti.fi/settings/smsSend");

		HtmlForm form = page.getFormByName("login_form")

		HtmlSubmitInput button = form.getInputByName("login")
		HtmlTextInput usernameInput = form.getInputByName("username")
		HtmlPasswordInput passwordInput = form.getInputByName("password")
		usernameInput.setValueAttribute(username)
		passwordInput.setValueAttribute(password)

		print 'Logging in ... '
		HtmlPage smsPage = button.click();
		println 'OK'

		HtmlForm smsform = smsPage.getFormByName("myform")
		HtmlSubmitInput sendButton = smsform.getInputByName("send")
		HtmlTextArea text = smsform.getTextAreaByName("text")
		HtmlTextArea number = smsform.getTextAreaByName("recipients")

		number.setText('0456516722')
		text.setText(message)

		println 'Sending message'
		def sentPage = sendButton.click()
		println 'Message sent'
	}
}