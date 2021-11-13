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
                <div class="card-header">{{ __('Inscriptions') }}</div>
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
                            @foreach($jsoninscriptions['data'] as $inscription)
                                @foreach($jsonevents['data'] as $event)
                                <tr>
                                    @if($event['id'] === $inscription['event_id'])
                                        <td>{{ $event['description'] }}</td>
                                        <td>{{ $event['event_date'] }}</td>
                                        <td>
                                            <form method="POST" action="/cancelinscription">
                                                @csrf
                                                    <input type="hidden" name="user_email" value="{{Session::get('email')}}">
                                                    <input type="hidden" name="event_id" value="{{ $event['id'] }}">
                                                    <input type="hidden" name="date" value="{{ Carbon\Carbon::now()->format('Y-m-d') }}">
                                                    <button type="submit" class="btn btn-secondary">Cancel</button>
                                            </form>
                                            <a href="/certificate/{{ $inscription['id'] }}"><button class="btn btn-info">Certificate</button></a>
                                        </td>
                                    @endif
                                </tr>
                                @endforeach
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
