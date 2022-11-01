package controllers

import play.api.libs.json.{JsError, JsSuccess, JsValue}

import javax.inject._
import play.api.mvc._
import services.{AccountService, ManageAccountService}

@Singleton
class DepositController @Inject()(val controllerComponents: ControllerComponents,
                                  accountService: AccountService,
                                  manageAccountService: ManageAccountService) extends BaseController {

  private val amountKey = "amount"

  def deposit(accountNumber: String): Action[JsValue] = Action(parse.json) { implicit request =>
    (request.body \ amountKey).validate[Double] match {
      case JsSuccess(amount, _) =>
        accountService.getAccount(accountNumber) match {
          case Some(account) =>
            Ok(manageAccountService.deposit(account, amount).bankBalance.toString)
          case _ => NotFound(s"Account number $accountNumber doesn't exist")
        }
      case JsError(_) =>
        BadRequest(s"key '$amountKey' wasn't provided")
    }
  }


}
