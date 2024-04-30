from schemas import HTTPMsg

json = {
    "id": 7,
    "lastname": "Miller",
    "firstname": "Frank",
    "gender": "M",
    "birthday": "1980-01-21T23:00:00Z",
    "cardnumber": "4556737586899855",
}


def average_age(clients):
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
    
def ratio_women(clients):
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
    
        