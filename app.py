from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates
from fastapi.responses import JSONResponse, PlainTextResponse
import requests

app = FastAPI()
templates = Jinja2Templates(directory="templates")

def fetch_data(url):
    response = requests.get(url)
    return response.json()

@app.get("/", response_class=PlainTextResponse)
def hello(request: Request, use_template: bool = False):
    if use_template:
        return templates.TemplateResponse("hello.html", {"request": request})
    else:
        return "Hello World!"

@app.get("/json")
def json_view():
    data = {
        "vetList": [
            {
                "id": 1,
                "firstName": "James",
                "lastName": "Carter",
                "specialties": [],
                "nrOfSpecialties": 0,
                "new": "false",
            },
        ]
    }
    return JSONResponse(content=data)

@app.get("/analytics")
def analytics():
    # URLs des API
    url_api1 = "https://api.example1.com/data"
    url_api2 = "https://api.example2.com/data"

    # Appel des API
    data1 = fetch_data(url_api1)
    data2 = fetch_data(url_api2)

    # Analyser les données reçues
    result = analyze_data(data1, data2)

    # Retourner le résultat des analyses
    return JSONResponse(content=result)

def analyze_data(data1, data2):
    # Implémentez votre logique d'analyse, assurez-vous que les champs 'some_field' existent
    sum_value = data1.get("some_field", 0) + data2.get("some_field", 0)
    return {"result": sum_value}

