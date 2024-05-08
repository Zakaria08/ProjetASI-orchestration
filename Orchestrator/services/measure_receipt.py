from schemas import Receipt


def most_purchased_item(receipts: list[Receipt]):
    if not receipts:
        raise Exception("Error: receipts list is empty")
    else:
        items = {}
        for receipt in receipts:
            itemsPurchased = receipt.itemsPurchased
            if itemsPurchased in items:
                items[itemsPurchased] += 1
            else:
                items[itemsPurchased] = 1
            most_purchased_item = max(items, key=items.get)
        return most_purchased_item


def average(receipts: list[Receipt]):
    if not receipts:
        raise Exception("Error: receipts list is empty")
    else:
        total = 0
        for receipt in receipts:
            total += receipt.totalAmount
        average = total / len(receipts)
        return average


def best_payment_method(receipts: list[Receipt]):
    if not receipts:
        raise Exception("Error: receipts list is empty")
    else:
        payment_methods = {}
        for receipt in receipts:
            paymentMethod = receipt.paymentMethod
            if paymentMethod in payment_methods:
                payment_methods[paymentMethod] += 1
            else:
                payment_methods[paymentMethod] = 1
            best_payment_method = max(payment_methods, key=payment_methods.get)
        return best_payment_method
