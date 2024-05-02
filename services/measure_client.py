from schemas import HTTPMsg, Client

def averageage(clients: list[Client]):
    if not clients:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les clients(vide)",
            content={"error": "clients list is empty"},
        )
    else:
        total = 0
        for client in clients:
            total += client.age
        average = total / len(clients)
        return average
    
def ratiowomen(clients: list[Client]):
    if not clients:
        raise HTTPMsg(
            status=500,
            message="Le serveur a rencontré une erreur en essayant de récupérer les informations concernant les clients(vide)",
            content={"error": "clients list is empty"},
        )
    else:
        totalwomen = 0
        for client in clients:
            if client.gender == "F":
                totalwomen += 1
        return (totalwomen / len(clients)) * 100 + "%"
    
        