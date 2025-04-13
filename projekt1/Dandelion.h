#pragma once
#include "Plant.h"

#define SYMBOL 'm'
#define STRENGTH 0
#define SPREAD_ATTEMPTS 3

class Dandelion : public Plant
{
public:
    public:
    virtual void takeAction() override;
    Dandelion(World &wR, const int &xPos, const int &yPos);
    ~Dandelion();
};