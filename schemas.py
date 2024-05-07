from pydantic import BaseModel
from typing import Any


class Client(BaseModel):
    id: int
    lastname: str
    firstname: str
    gender: str
    birthday: int
    cardnumber: str


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


class HTTPMsg(BaseModel):
    status: int
    message: str
    content: Any
