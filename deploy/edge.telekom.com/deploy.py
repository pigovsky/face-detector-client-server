import json
import os
from typing import Any

import requests


DOCKER_IMG = "docker.edge.telekom.com/fh-dortmund-emulate/images/face-detector:0.0.0"
ORG = "FH-Dortmund-Emulate"
APP_DEFINITION = {
    "name": "face-detector",
    "organization": ORG,
    "version": "0.0.0"
}
REGION = {
    "Region": "EU"
}


def login() -> str:
    PASS = os.getenv("PASS")
    USER = os.getenv("USER")
    resp = requests.post(
        f"{API_HOST}/api/v1/login",
        json={
            "password": PASS,
            "username": USER
        }
    )
    return resp.json()["token"]


def get_zones(token: str) -> Any:
    resp = requests.post(
        f"{API_HOST}/api/v1/auth/ctrl/ShowZone",
        headers={
            "Authorization": f"Bearer {token}"
        },
        json=REGION,
    )
    text = '['+','.join(resp.text.splitlines())+']'
    data = json.loads(text)
    return list(map(
        lambda x: x.get('data', {})
            .get('key', {}),
        data
    ))


def create_app(token: str) -> Any:
    resp = requests.post(
        f"{API_HOST}/api/v1/auth/ctrl/CreateApp",
        headers={
            "Authorization": f"Bearer {token}"
        },
        json={
            "App": {
                "access_ports": "tcp:9998",
                "default_flavor": {
                    "name": "m4.medium"
                },
                "deployment": "docker",
                "image_path": DOCKER_IMG,
                "image_type": "Docker",
                "key": APP_DEFINITION
            },
            **REGION,
        }
    )
    return resp.json()


def create_cluster_inst(token: str) -> Any:
    resp = requests.post(
        f"{API_HOST}/api/v1/auth/ctrl/CreateClusterInst",
        headers={
            "Authorization": f"Bearer {token}"
        },
        json={
            "ClusterInst": {
                "key": {
                    "cloudlet_key": {
                        "organization": "TDG",  # The organization providing the cloudlet
                        "name": "dusseldorf-main"
                    },
                    "name": "default",
                    "organization": APP_DEFINITION["organization"],
                },
            },
            **REGION,
        }
    )
    return resp.json()


def create_app_inst(token: str, zone: dict) -> Any:
    resp = requests.post(
        f"{API_HOST}/api/v1/auth/ctrl/CreateAppInst",
        headers={
            "Authorization": f"Bearer {token}"
        },
        json={
            **REGION,
            "appinst": {
                "key": {
                    "name": f'{APP_DEFINITION["name"]}-{zone["name"]}',
                    "organization": ORG
                },
                "app_key": APP_DEFINITION,
                "zone_key": zone
            }
        }
    )
    return resp.text


if __name__ == "__main__":
    API_HOST = os.getenv("API_HOST")
    token = login()
    print(token)
    zones = get_zones(token)
    print(zones)
    app = create_app(token)
    print(app)
    # cluster_inst = create_cluster_inst(token)
    # print(cluster_inst)
    for zone in zones:
        app_inst = create_app_inst(token, zone)
        print(app_inst)
