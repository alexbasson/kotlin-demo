import {
  ClientService,
  CreateClientResultHandler,
  GetClientOverviewsResultHandler,
  GetClientResultHandler
} from './ClientService';
import {CreateClientRequest} from './Client';

export class DefaultClientService implements ClientService {

  private baseUrl = 'http://localhost:8080/api';

  getClientOverviews(resultHandler: GetClientOverviewsResultHandler): void {
    fetch(this.baseUrl + '/clients')
      .then(response => response.json())
      .then(clientOverviews => resultHandler.success(clientOverviews))
      .catch(error => console.error(error));
  }

  getClient(id: string, resultHandler: GetClientResultHandler): void {
    fetch(this.baseUrl + `/clients/${id}`)
      .then(response => response.json())
      .then(client => resultHandler.success(client))
      .catch(error => console.error(error));
  }

  createClient(request: CreateClientRequest, resultHandler: CreateClientResultHandler): void {
    fetch(this.baseUrl + '/clients', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(request)
    })
      .then(response => response.json())
      .then(client => resultHandler.createdClient(client))
      .catch(error => console.error(error))
  }

}
