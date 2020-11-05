import requests
import json

port = "8081"
url_api_endpoint = "http://localhost:{port}/api".format(port=port)


class CarDatabaseRequest(object):
    def __init__(self, *args):
        super(CarDatabaseRequest, self).__init__(*args)

    def get(self, url_api_endpoint=url_api_endpoint):
        response = requests.get(
            url=url_api_endpoint
        )
        self.json_printer(response)
        return response.json()

    def post(self, url_api_endpoint=url_api_endpoint, payload={}):
        headers =  {
                'content-type' : 'application/json',
                'Cache-Control' : "no-cache"
            }

        response = requests.post(
            url = url_api_endpoint,
            data = json.dumps(payload),
            headers = headers,
            verify = False
        )

        print(response)
        self.json_printer(response.json())
        return response.json()

    def get_api(self):
        print(f"\nGET : API")
        json_response = self.get(
            url=url_api_endpoint
        )
        return json_response

    def get_cars(self):
        print(f"\nGET : Cars")
        json_response = requests.get(url="{url}/cars"
                                     .format(
                                         url=url_api_endpoint
                                     )
                                     ).json()
        self.json_printer(json_response)

    def get_owners(self):
        print(f"\nGET : Owners")
        json_response = requests.get(url="{url}/owners"
                                     .format(
                                         url=url_api_endpoint
                                     )
                                     ).json()

        self.json_printer(json_response)

    def get_car(self, car_id):
        print(f"\nGET : Car")
        json_response = requests.get(url="{url}/cars/{car_id}"
                                     .format(
                                         url=url_api_endpoint,
                                         car_id=car_id
                                     )
                                     ).json()
        self.json_printer(json_response)

    def get_owner(self, owner_id):
        print(f"\nGET : Owner")
        json_response = requests.get(url="{url}/owners/{owner_id}"
                                     .format(
                                         url=url_api_endpoint,
                                         owner_id=owner_id
                                     )
                                     ).json()
        self.json_printer(json_response)

    def get_owner_from_car(self, car_id):
        print(f"\nGET : Owner from car")
        json_response = requests.get(url="{url}/cars/{car_id}/owner"
                                     .format(
                                         url=url_api_endpoint,
                                         car_id=car_id
                                     )
                                     ).json()
        self.json_printer(json_response)

    def get_cars_from_owner(self, owner_id):
        print(f"\nGET : Cars from owner")
        json_response = requests.get(url="{url}/owners/{owner_id}/cars"
                                     .format(
                                         url=url_api_endpoint,
                                         owner_id=owner_id
                                     )
                                     ).json()
        self.json_printer(json_response)

    def post_car(self, params={}):
        print(f"\nPOST : Car into cars")
        endpoint = "{url_api_endpoint}/cars".format(
            url_api_endpoint=url_api_endpoint
        )

        return self.post(
            url_api_endpoint=endpoint,
            payload=params
        )

    @staticmethod
    def json_printer(json_obj):
        json_to_print = json.dumps(json_obj, indent=1)
        print(json_to_print)


def run_getters(carDBRequest: CarDatabaseRequest = CarDatabaseRequest()):
    # carDBRequest.get_api()
    # carDBRequest.get_cars()
    # carDBRequest.get_owners()
    # carDBRequest.get_car(id=3)
    # carDBRequest.get_owner(id=1)
    # carDBRequest.get_owner_from_car(car_id=3)
    carDBRequest.get_cars_from_owner(owner_id=2)


def post_cars(carDBRequest: CarDatabaseRequest = CarDatabaseRequest):
    body = {
        "brand": "Lamborghini",
        "model": "Aventador",
        "color": "Black",
        "year": 2021,
        "price": 411000
    }
    carDBRequest.post_car(params=body)


def run_posts(carDBRequest: CarDatabaseRequest = CarDatabaseRequest()):
    post_cars(carDBRequest)


def main():
    run_getters()
    run_posts()


if __name__ == '__main__':
    main()
