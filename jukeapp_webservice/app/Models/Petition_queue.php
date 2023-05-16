<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class petition_queue extends Model
{
    use HasFactory;

    public $table = "petition_queue";

    public $timestamps = false;

    protected $fillable = [
        'id',
        'pq_son_id',
        'pq_users_id',
        'pq_moment_temp',
    ];
}
