<?php

use App\Models\User;
use Faker\Factory;
use Laravel\Lumen\Testing\DatabaseMigrations;
use Laravel\Lumen\Testing\DatabaseTransactions;

class LoginTest extends TestCase
{
    protected function user() {
        return User::factory()->create();
    }
    /**
     * A basic test example.
     *
     * @return void
     */
    public function testShouldLoginUser()
    {
        $params = [
            'email' => $this->user()->email,
            'password' => "password"
        ];

        $this->post('/auth/login', $params, [])
                ->seeJsonStructure([
                    "success",
                    "status",
                    "message",
                    "data" => [
                        "user" => [
                            "id",
                            "name",
                            "email",
                            "created_at",
                            "updated_at"
                        ],
                        "access_token",
                        "token_type",
                        "expires_in"
                    ]
                ])
                ->seeStatusCode(200);
    }

    public function testShouldNotLoginNonExistentUser()
    {
        $params = [
            'email' => 'nonexistent@gmail.com',
            'password' => 'mypassword'
        ];

        $this->post('/auth/login', $params, [])
             ->seeJsonContains([
                 "message" => "Invalid username or password"
             ])
             ->seeStatusCode(401);
    }

}
