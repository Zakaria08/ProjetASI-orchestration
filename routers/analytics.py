from schemas import HTTPMsg
from fastapi import APIRouter
from services import analyze_data
from services.data_fetcher import fetch_data
from services.data_unmarshaller import unmarshall_receipt, unmarshall_client

router = APIRouter()

@router.get("/analytics")
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

