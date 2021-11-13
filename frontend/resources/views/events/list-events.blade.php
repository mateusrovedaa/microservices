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
                <div class="card-header">{{ __('Events') }}</div>
                <div class="card-body">

                    <a href="/home"><button class="btn btn-secondary">Back to home</button></a>

                    <div class="table-responsive">
                        </br>

                        <table class="table table-condensed table-striped">
                            <thead>
                                <tr>
                                    <th>Description</th>
                                    <th>Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>

                            @foreach($jsonevents['data'] as $event)
                                <tr>
                                    <td>{{ $event['description'] }}</td>
                                    <td>{{ $event['event_date'] }}</td>
                                    <td>
                                        <a href="/inscription/{{ $event['id'] }}"><button class="btn btn-info">Inscription</button></a>
                                    </td>
                                    @if($errors->any())
                                        <div class="alert alert-danger">
                                            {{ implode('', $errors->all(':message')) }}
                                        </div>
                                    @endif
                                    @if(session('status'))
                                        <div class="alert alert-success">
                                            {{ session('status') }}
                                        </div>
                                    @endif
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
