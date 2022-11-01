package services

import models.BankAccount

class ManageAccountService {

  def deposit(bankAccount: BankAccount, amount: Double): BankAccount =
    bankAccount.copy(bankBalance = bankAccount.bankBalance + amount)

}
