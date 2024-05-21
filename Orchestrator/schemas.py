from pydantic import BaseModel,Extra
from typing import Any


class Client(BaseModel):
    lastname: str
    firstname: str
    gender: str
    birthday: str
    cardnumber: str
    class Config:
        extra = Extra.allow


class Receipt(BaseModel):
    id: int
    receiptNumber: int
    purchaseDate: str
    itemsPurchased: str
    quantity: int
    pricePerItem: float
    totalAmount: float
    paymentMethod: str
    storeLocation: str
    cashierName: str
    cardNumber: str

class Sum(BaseModel):
    sum: float

class MostPurchased(BaseModel):
    purchasedItem: str
    quantity: int

class BestPaymentMethod(BaseModel):
    paymentMethod: str
    quantity: float

class HTTPMsg(BaseModel):
    status: int
    message: str
    content: Any
