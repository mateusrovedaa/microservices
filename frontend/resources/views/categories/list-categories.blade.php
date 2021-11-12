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
                <div class="card-header">{{ __('Categories listagem') }}</div>
                <div class="card-body">

                    <a href="/create-category"><button class="btn btn-primary">Add new category</button></a>
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
                                @foreach($categories as $category)
                                <tr>
                                    <td>{{ $category->name }}</td>
                                    <td>
                                        <a href="/edit-category/{{ $category->id }}"><button class="btn btn-info">Edit</button></a>
                                        <a href="/delete-category/{{ $category->id }}"><button class="btn btn-danger">Delete</button></a>
                                    </td>
                                    @if($errors->any())
                                        <div class="alert alert-danger">
                                            {{ implode('', $errors->all(':message')) }}
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