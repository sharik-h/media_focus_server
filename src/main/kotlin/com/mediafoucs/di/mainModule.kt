package com.mediafoucs.di

import com.mediafoucs.db.Expense.ExpenseDaoImpl
import com.mediafoucs.db.Payments.PaymentDaoImpl
import com.mediafoucs.db.Users.UsersDaoImpl
import org.koin.dsl.module

val mainModule = module {
    single<PaymentDaoImpl> {
        PaymentDaoImpl()
    }
    single<UsersDaoImpl> {
        UsersDaoImpl()
    }
    single<ExpenseDaoImpl>{
        ExpenseDaoImpl()
    }
}