package authz

default allow_transfer = false
default allow_loan_order = false
default allow_payment = false

allow_transfer = true {
	is_transfer
    is_valid_transfer
}

allow_loan_order = true {
	is_loan_order
    is_valid_loan_order
}

allow_payment = true {
	is_payment
    is_valid_payment
}

is_payment {
	input.operation =="PAYMENT"
}

is_transfer {
	input.operation =="TRANSFER"
}

is_loan_order {
	input.operation =="LOAN_ORDER"
}

is_valid_payment {
	payload := get_payload
    user := get_user_if_is_valid
    merchant := get_merchant_if_is_valid
    check_payment_amount_range(payload.amount, merchant)

}

is_valid_transfer {
	payload := get_payload
    user := get_user_if_is_valid
    check_amount_and_user_score_for_transfer(user, payload)
}

is_valid_loan_order {
	payload := get_payload
    user := get_user_if_is_valid
    check_user_score_and_registration_date_for_loan(user)
}

check_user_score_and_registration_date_for_loan(user){
    user.score >= 6
    registeredAt := time.add_date(time.parse_rfc3339_ns(user.registeredAt), 1, 6, 0)
    registeredAt <= time.now_ns()

}

check_payment_amount_range(amount, merchant) {
	amount >= merchant.minAmount
    amount <= merchant.maxAmount
}

check_amount_and_user_score_for_transfer(user, payload) {
	payload.amount <= 1000
}
{
	payload.amount > 1000
    user.score >= 4
}

get_user_if_is_valid := user {
	payload := get_payload
	user:=data.users[payload.user]
    not is_null(user)
}

get_payload := payload {
	payload := input.payload
}

get_merchant_if_is_valid := merchant {
	payload := get_payload
	merchant:=data.merchants[payload.merchant]
    not is_null(merchant)
}




