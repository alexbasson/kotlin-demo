import * as React from "react";
import {ReactElement} from "react";
import {Client} from "./Client";
import {ClientService, GetClientsResultHandler} from "./ClientService";
import {ClientListRow} from "./ClientListRow";

interface Props {
  clientService: ClientService;
}

interface State {
  clients: Client[];
}

export class ClientList extends React.Component<Props, State> implements GetClientsResultHandler {

  constructor(props: Props) {
    super(props);
    this.state = {
      clients: []
    }
  }

  componentDidMount(): void {
    this.props.clientService.getClients(this);
  }

  render(): ReactElement {
    return (
      <div className="card">
        <h3>Clients</h3>
        <ul>
          {this.state.clients.map((client) => <ClientListRow key={client.id} client={client}/>)}
        </ul>
      </div>
    );
  }

  success(clients: Client[]) {
    this.setState({clients});
  }

}
