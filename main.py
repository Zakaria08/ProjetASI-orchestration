from fastapi import FastAPI
from routers import most_purchased_item, hello, average_receipt, best_paymentmethod

app = FastAPI(title="ProjetASI Orchestrateur")

app.include_router(hello.router)
app.include_router(most_purchased_item.router)
app.include_router(average_receipt.router)
app.include_router(best_paymentmethod.router)
