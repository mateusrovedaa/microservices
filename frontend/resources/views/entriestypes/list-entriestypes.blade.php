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
                <div class="card-header">Entries types list</div>
                <div class="card-body">
                   
                    <a href="/create-entrytype"><button class="btn btn-primary">Add new entry type</button></a>
                    <a href="/home"><button class="btn btn-secondary">Back to home</button></a>
                    
                    <div class="table-responsive">
                        </br>

                        <table class="table table-condensed table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                @foreach($entriestypes as $entrytype)
                                <tr>
                                    <td>{{ $entrytype->name }}</td>
                                    <td>
                                        <a href="/edit-entrytype/{{ $entrytype->id }}"><button class="btn btn-info">Edit</button></a>
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