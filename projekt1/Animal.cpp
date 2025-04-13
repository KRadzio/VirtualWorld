#include "Animal.h"

bool Animal::isTheSameSpecies(Organism *other) const
{
    if (this->type == other->getType())
        return true;
    else
        return false;
}

void Animal::reproduce(Organism *other)
{
    int newY = this->yPos;
    int newX = this->xPos;
    if (this->age >= REPRODUCE_AGE && other->getAge() >= REPRODUCE_AGE && this != other)
    {
        if (this->worldRef.isPositionInsideWorld(newX, newY - 1) && this->worldRef.isPositionFree(newX, newY - 1))
        {
            newY--;
            this->worldRef.addNewOrganism(this, newX, newY);
        }
        else if (this->worldRef.isPositionInsideWorld(newX, newY + 1) && this->worldRef.isPositionFree(newX, newY + 1))
        {
            newY++;
            this->worldRef.addNewOrganism(this, newX, newY);
        }
        else if (this->worldRef.isPositionInsideWorld(newX + 1, newY) && this->worldRef.isPositionFree(newX + 1, newY))
        {
            newX++;
            this->worldRef.addNewOrganism(this, newX, newY);
        }
        else if (this->worldRef.isPositionInsideWorld(newX - 1, newY) && this->worldRef.isPositionFree(newX - 1, newY))
        {
            newX--;
            this->worldRef.addNewOrganism(this, newX, newY);
        }

        if (newY != this->yPos || newX != this->xPos)
        {
            string event = "Pojawil sie nowy organism typu " + this->typeS + "na polu (" + std::to_string(newX) + "," + std::to_string(newY) + ")";
            this->worldRef.addEvent(event);
        }
    }
}

void Animal::move(int newX, int newY)
{
    this->xPos = newX;
    this->yPos = newY;
}
void Animal::takeAction()
{
    this->prevX = this->yPos;
    this->prevY = this->yPos;
    int newY = this->yPos;
    int newX = this->xPos;
    bool moveIsValid = false;
    while (moveIsValid == false)
    {
        int random = rand() % 4;
        switch (random)
        {
        case 0:
            if (this->worldRef.isPositionInsideWorld(newX, newY - this->moveRange))
            {
                newY--;
                moveIsValid = true;
            }
            break;
        case 1:
            if (this->worldRef.isPositionInsideWorld(newX, newY + this->moveRange))
            {
                newY++;
                moveIsValid = true;
            }
            break;
        case 2:
            if (this->worldRef.isPositionInsideWorld(newX + this->moveRange, newY))
            {
                newX++;
                moveIsValid = true;
            }
            break;
        case 3:
            if (this->worldRef.isPositionInsideWorld(newX - this->moveRange, newY))
            {
                newX--;
                moveIsValid = true;
            }
            break;
        }
    }
    if (this->worldRef.isPositionFree(newX, newY) != true)
        colision(this->worldRef.getOrganism(newX, newY));
    if(this->worldRef.isPositionFree(newX, newY) || this->worldRef.getOrganism(newX, newY)->checkIfIsAlive() == false)
        move(newX, newY);
}
void Animal::colision(Organism *other)
{
    if (this->isTheSameSpecies(other))
    {
        reproduce(other);
        return;
    }
    else if (other->specialAbility(this))
        return;
    else if (this->strength > other->getStrength() && this != other)
    {
        other->die();
        string event = this->typeS + " zabija " + other->getTypeString();
        this->worldRef.addEvent(event);
    }
    else if (this != other)
    {
        this->die();
        string event = other->getTypeString() + " zabija " + this->typeS;
        this->worldRef.addEvent(event);
    }
}

bool Animal::isAnAnimal() const { return true; }

Animal::Animal(World &wR, const int &xPos, const int &yPos, const char &symbol, const int &strength, const int &initiative, AllOrganisms type)
    : Organism(wR, xPos, yPos, symbol, strength, initiative, type) {}
Animal::~Animal() {}