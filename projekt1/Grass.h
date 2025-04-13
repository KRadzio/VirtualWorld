#pragma once
#include "Plant.h"

#define SYMBOL 't'
#define STRENGTH 0

class Grass : public Plant
{
public:
    public:
    Grass(World &wR, const int &xPos, const int &yPos);
    ~Grass();
};
