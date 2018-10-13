import React from 'react';
import ReactDOM from 'react-dom';
import {CustomPaging, PagingState} from '@devexpress/dx-react-grid';
import {Grid, PagingPanel, Table, TableColumnVisibility, TableHeaderRow} from '@devexpress/dx-react-grid-material-ui';
import {Button, Paper, TextField} from '@material-ui/core';
import {Loading} from "./loading/loading";


const URL = '/search';
const styles = {
    answered: {
        backgroundColor: '#a2e2a4',
    },
    opened: {
        backgroundColor: '#ffffff',
    }
};
const TableRow = ({row, ...restProps}) => (
    <Table.Row
        {...restProps}
        onClick={() => window.open(row.url, "_blank")}
        style={{
            cursor: 'pointer',
            ...styles[row.status.toLowerCase()],
        }}
    />
);

class App extends React.PureComponent {
    constructor() {
        super();

        this.state = {
            columns: [
                {name: 'title', title: 'Title'},
                {name: 'author', title: 'Author'},
                {name: 'date', title: 'Date'},
                {name: 'url', title: 'URL'},
                {name: 'status', title: 'status'}
            ],
            searchValue: "",
            rows: [],
            totalCount: 0,
            pageSize: 10,
            currentPage: 0
        };

        this.changeCurrentPage = this.changeCurrentPage.bind(this);
        this.changeSearchValue = this.changeSearchValue.bind(this);
        this.loadData = this.loadData.bind(this);
    }

    changeSearchValue(event) {
        this.state.searchValue = event.target.value;
    }

    changeCurrentPage(currentPage) {
        this.state.currentPage = currentPage;
        this.loadData();
    }

    loadData() {
        this.setState({loading: true});
        const {searchValue, pageSize, currentPage} = this.state;
        fetch(URL, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: searchValue,
                pageSize: pageSize,
                pageNum: currentPage + 1
            })
        })
            .then(response => response.json())
            .then(data => this.setState({
                rows: data.questions,
                totalCount: data.totalCount,
                loading: false,
            }))
            .catch(() => this.setState({loading: false}));
    }

    render() {
        const {
            rows, columns, pageSize, currentPage, totalCount, loading
        } = this.state;

        return (
            <Paper style={{position: 'relative'}}>
                <div align="center">
                    <TextField type="text" onChange={this.changeSearchValue}/>
                    <Button onClick={this.loadData}>
                        Search
                    </Button>
                </div>
                <Grid
                    rows={rows}
                    columns={columns}
                >
                    <PagingState
                        currentPage={currentPage}
                        onCurrentPageChange={this.changeCurrentPage}
                        pageSize={pageSize}
                    />
                    <CustomPaging
                        totalCount={totalCount}
                    />
                    <Table rowComponent={TableRow}/>
                    <TableColumnVisibility
                        defaultHiddenColumnNames={['status', 'url']}
                    />
                    <TableHeaderRow/>
                    <PagingPanel/>
                </Grid>
                {loading && <Loading/>}
            </Paper>
        );
    }
}

ReactDOM.render(<App/>, document.getElementById('react'));