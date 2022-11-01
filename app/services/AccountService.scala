package services

import models.BankAccount

class AccountService {

  def getAccount(accountNumber: String): Option[BankAccount] =
    Seq(
      BankAccount("1", 1),
      BankAccount("2", 2)
    ).find(_.accountNumber == accountNumber)

}
