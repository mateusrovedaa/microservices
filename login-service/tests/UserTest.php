<?php

use App\Models\User;
use Laravel\Lumen\Testing\DatabaseMigrations;
use Laravel\Lumen\Testing\DatabaseTransactions;

class UserTest extends TestCase
{
    protected function user() {
        return User::factory()->create();
    }
    /**
     * A basic test example.
     *
     * @return void
     */
    public function testShouldGetAllUsers()
    {

        $this->actingAs($this->user())->get('/auth/users', [])
                ->seeStatusCode(200);
    }

    public function testShouldGetSingleUser()
    {
        $this->actingAs($this->user())->get('/auth/users/1')
            ->seeJsonStructure([
                "success",
                "status",
                "message",
                "data" => [
                    "id",
                    "name",
                    "email",
                    "created_at",
                    "updated_at"
                ],
            ])
            ->seeStatusCode(200);
    }

}
