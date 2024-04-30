from pydantic import BaseModel

class Client(BaseModel):
    id: int
    lastname: str
    firstname: str
    gender: str
    age: int
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
    