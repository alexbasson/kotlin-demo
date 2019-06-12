import * as React from 'react';
import {ReactElement} from 'react';
import './Card.css';
import './ClientOverviewCard.css';
import {ClientService, CreateClientResultHandler, GetClientOverviewsResultHandler} from './ClientService';
import {ClientOverviewRow} from './ClientOverviewRow';
import {ClientOverview} from './ClientOverview';
import {Client} from './Client';
import {CreateClientForm} from './CreateClientForm';

interface Props {
  clientService: ClientService;
}

interface State {
  clientOverviews: ClientOverview[];
  displayForm: boolean;
}

export class ClientOverviewCard
  extends React.Component<Props, State>
  implements GetClientOverviewsResultHandler, CreateClientResultHandler {

  constructor(props: Props) {
    super(props);
    this.state = {
      clientOverviews: [],
      displayForm: false,
    };

    this.toggleDisplayForm = this.toggleDisplayForm.bind(this);
  }

  componentDidMount(): void {
    this.props.clientService.getClientOverviews(this);
  }

  render(): ReactElement {
    return (
      <div className="card client-overview-card">
        <div className="card-header fdr fjb">
          <h3>Clients</h3>
          <button onClick={this.toggleDisplayForm}>
            +
          </button>
        </div>
        <ul>
          <li className="border-bottom">
            <p className="strong align-left">Name</p>
            <p className="strong align-left">Email</p>
            <p className="strong align-center">Number of Accounts</p>
          </li>
          {this.state.clientOverviews.map((clientOverview) =>
            <ClientOverviewRow
              key={clientOverview.client.id}
              clientOverview={clientOverview}
            />
          )}
        </ul>

        {
          this.state.displayForm ?
            <CreateClientForm
              clientService={this.props.clientService}
              createClientResultHandler={this}
            />
            :
            ''
        }
      </div>
    );
  }

  toggleDisplayForm() {
    this.setState({displayForm: !this.state.displayForm});
  }

  success(clients: ClientOverview[]) {
    this.setState({clientOverviews: clients});
  }

  createdClient(client: Client): void {
    this.setState({displayForm: false});
    this.props.clientService.getClientOverviews(this);
  }

}
