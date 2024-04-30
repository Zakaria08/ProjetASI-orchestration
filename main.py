from fastapi import FastAPI
from routers import analytics, hello

app = FastAPI(title="ProjetASI Orchestrateur")

app.include_router(hello.router)
app.include_router(analytics.router)
