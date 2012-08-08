def config = new ConfigSlurper().parse(new File('config.groovy').toURL())
def sender = new SmsSender()
sender.sendSms('Test', config.username, config.password)
