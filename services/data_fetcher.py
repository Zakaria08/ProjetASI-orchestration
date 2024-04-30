from schemas import HTTPMsg
import requests

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
