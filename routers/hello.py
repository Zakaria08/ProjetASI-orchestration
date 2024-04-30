from fastapi import APIRouter, Request
from fastapi.responses import PlainTextResponse
from fastapi.templating import Jinja2Templates

router = APIRouter()
templates = Jinja2Templates(directory="templates")

@router.get("/", response_class=PlainTextResponse)
def hello(request: Request, use_template: bool = False):
    if use_template:
        return templates.TemplateResponse("hello.html", {"request": request})
    else:
        return "Hello World!"
