@extends('layouts.app')

@section('content')
<style>
tr td:last-child {
    width: 1%;
    white-space: nowrap;
}
</style>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Entries list</div>
                <div class="card-body">
                   
                    <a href="/create-entry"><button class="btn btn-primary">Add new entry</button></a>
                    <a href="/home"><button class="btn btn-secondary">Back to home</button></a>
                    
                    <div class="table-responsive">
                        </br>

                        <table class="table table-condensed table-striped">
                            <thead>
                                <tr>
                                    <th>Payment date</th>
                                    <th>Description</th>
                                    <th>Value</th>
                                    <th>Type</th>
                                    <th>Category</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                @foreach($entries as $entry)
                                <tr>
                                    <td>{{ $entry->payment_date }}</td>
                                    <td>{{ $entry->description }}</td>
                                    <td>{{ $entry->value }}</td>
                                    <td>{{ $entry->entrytype->name }}</td>
                                    <td>{{ $entry->category->name }}</td>
                                    <td>
                                        <a href="/edit-entry/{{ $entry->id }}"><button class="btn btn-info">Edit</button></a>
                                    </td>
                                </tr>
                                @endforeach
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection