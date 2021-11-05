<?php


return [
    'register' => [
        'base_uri' => env('REGISTER_SERVICE_BASE_URI'),
        'secret' => env('REGISTER_SERVICE_SECRET'),
    ],
    'login' => [
        'base_uri' => env('LOGIN_SERVICE_BASE_URI'),
        'secret' => env('LOGIN_SERVICE_SECRET'),
    ],
    'checkin' => [
        'base_uri' => env('CHECKIN_SERVICE_BASE_URI'),
        'secret' => env('CHECKIN_SERVICE_SECRET'),
    ],
    'email' => [
        'base_uri' => env('EMAIL_SERVICE_BASE_URI'),
        'secret' => env('EMAIL_SERVICE_SECRET'),
    ],
    'event' => [
        'base_uri' => env('EVENT_SERVICE_BASE_URI'),
        'secret' => env('EVENT_SERVICE_SECRET'),
    ]
];
