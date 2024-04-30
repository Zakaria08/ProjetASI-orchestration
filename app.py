from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates
from fastapi.responses import PlainTextResponse
from pydantic import BaseModel, ValidationError
import requests

app = FastAPI()
templates = Jinja2Templates(directory="templates")


class HTTPMsg(BaseModel):
    status: int
    message: str



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


def unmarshall_receipt(receipt_data) -> Receipt:
    try:
        receipt = Receipt(**receipt_data)
        return receipt
    except ValidationError as e:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les tickets de caisses",
            content={"error": str(e)},
        )


def unmarshall_client(client_data) -> Client:
    try:
        client = Client(**client_data)
        return client
    except ValidationError as e:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les clients",
            content={"error": str(e)},
        )


def fetch_data(url):
    try:
        response = requests.get(url)
        response.raise_for_status()
        return response.json()
    except requests.RequestException as e:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les tickets de caisses",
            content={"error": str(e)},
        )


@app.get("/", response_class=PlainTextResponse)
def hello(request: Request, use_template: bool = False):
    if use_template:
        return templates.TemplateResponse("hello.html", {"request": request})
    else:
        return "Hello World!"

@app.get("/analytics")
def analytics() -> HTTPMsg:
    url_api1 = "https://api.exemple.com/data"
    url_api2 = "https://api.exemple.com/data"
    message = None
    try:
        data1 = fetch_data(url_api1)
        data2 = fetch_data(url_api2)

        receipt = unmarshall_receipt(data1)
        client = unmarshall_client(data2)

        result = analyze_data(receipt, client)
        message = HTTPMsg(status=200, message="Success", content=result)
        return message

    except HTTPMsg as e:
        return e

def analyze_data(data1, data2):
    # TODO: Implement the logic to analyze the data
    return 200
