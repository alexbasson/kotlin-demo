import * as React from 'react';
import {ClientService, GetClientResultHandler} from './ClientService';
import {Client} from './Client';
import {AccountList} from "./AccountList";
import {AccountService} from "./AccountService";

interface Props {
    clientId: string;
    clientService: ClientService;
    accountService: AccountService;
}

interface State {
    client: Client;
}

export class ClientDetail extends React.Component<Props, State> implements GetClientResultHandler {

    constructor(props: Props) {
        super(props);
        this.state = {
            client: {} as Client
        }
    }

    componentDidMount(): void {
        const clientId = this.props.clientId;
        this.props.clientService.getClient(clientId, this);
    }

    render() {
        const client = this.state.client;
        return (
            <div>
                <h1>{client.firstName} {client.lastName}</h1>
                <h2>{client.email}</h2>

                <AccountList clientId={this.props.clientId} accountService={this.props.accountService} />
            </div>
        )
    }

    success(client: Client) {
        this.setState({client});
    }
}
