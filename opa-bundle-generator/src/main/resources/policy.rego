 package authz

 default allow = false

 allow = true {
 	is_transfer
    is_valid_transfer
 }

 allow = true {
 	is_loan_order
    is_valid_loan_order
 }

 allow = true {
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
    check_merchant_payment_time_interval(merchant)
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

 check_merchant_payment_time_interval(merchant) {
    current_time := time.clock(time.now_ns())
    current_minute := convert_time_to_minutes(current_time)
    start_minute := convert_time_to_minutes(merchant.startTime)
    finish_minute := convert_time_to_minutes(merchant.finishTime)
    check_merchant_payment_time_interval1(current_minute, start_minute, finish_minute)
 }

 check_merchant_payment_time_interval1(current_minute, start_minute, finish_minute) {
    start_minute >= finish_minute
    current_minute >= start_minute
 } else {
    start_minute >= finish_minute
    current_minute <= finish_minute
 } else {
 	start_minute < finish_minute
    current_minute >= start_minute
    current_minute <= finish_minute
 }

 check_amount_and_user_score_for_transfer(user, payload) {
    payload.amount <= 1000
 } else {
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

 convert_time_to_minutes(t) = minute {
	minute := t[0] * 60 + t[1]
 }
